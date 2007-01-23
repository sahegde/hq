/*
 * NOTE: This copyright does *not* cover user programs that use HQ
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 * "derived work".
 * 
 * Copyright (C) [2004, 2005, 2006], Hyperic, Inc.
 * This file is part of HQ.
 * 
 * HQ is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */

package org.hyperic.hq.measurement.server.session;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hyperic.dao.DAOFactory;
import org.hyperic.hq.appdef.shared.AppdefEntityID;
import org.hyperic.hq.appdef.shared.AppdefUtil;
import org.hyperic.hq.dao.HibernateDAO;
import org.hyperic.util.jdbc.DBUtil;
import org.hibernate.Query;

public class DerivedMeasurementDAO extends HibernateDAO {
    public DerivedMeasurementDAO(DAOFactory f) {
        super(DerivedMeasurement.class, f);
    }

    public DerivedMeasurement findById(Integer id) {
        return (DerivedMeasurement)super.findById(id);
    }

    public DerivedMeasurement get(Integer id) {
        return (DerivedMeasurement)super.get(id);
    }

    void remove(DerivedMeasurement entity) {
        super.remove(entity);
    }

    DerivedMeasurement create(Integer instanceId,
                              MeasurementTemplate mt,
                              long interval) {
        DerivedMeasurement dm = new DerivedMeasurement(instanceId, mt,
                                                       interval);

        dm.setEnabled(interval != 0);
        dm.setFormula(mt.getTemplate());
        save(dm);
        return dm;
    }

    List findByIds(Integer ids[]) {
        String sql = "from DerivedMeasurement where id IN (:ids)";

        return getSession().createQuery(sql)
            .setParameterList("ids", ids)
            .list();
    }

    public DerivedMeasurement findByTemplateForInstance(Integer tid,
                                                        Integer iid) {
        String sql =
            "select distinct m from DerivedMeasurement m " +
            "join m.template t " +
            "where t.id=? and m.instanceId=?";

        return (DerivedMeasurement)getSession().createQuery(sql)
            .setInteger(0, tid.intValue())
            .setInteger(1, iid.intValue()).uniqueResult();
    }

    List findByTemplate(Integer id) {
        String sql =
            "select distinct m from DerivedMeasurement m " +
            "join m.template t " +
            "where t.id=?";

        return getSession().createQuery(sql)
            .setInteger(0, id.intValue()).list();
    }

    List findByInstance(int type, int id) {
        String sql =
            "select distinct m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "where mt.appdefType=? and m.instanceId=? and " +
            "m.interval is not null";

        return getSession().createQuery(sql)
            .setInteger(0, type)
            .setInteger(1, id).list();
    }

    int deleteByInstances(AppdefEntityID[] ids)
    {
        Map map = AppdefUtil.groupByAppdefType(ids);
        StringBuffer sql = new StringBuffer()
            .append("DerivedMeasurement where ");
        for (int i = 0; i < map.size(); i++) {
            if (i > 0) {
                sql.append(" or ");
            }
            sql.append(" id in " +
                       "(select m.id from DerivedMeasurement m " +
                       "join m.template t " +
                       "join t.monitorableType mt where " +
                       "  m.interval is not null and ")
                .append("mt.appdefType=")
                .append(":appdefType"+i)
                .append(" and ")
                .append("m.instanceId in (:list" + i + ")")
                .append(") ");
        }
        // delete baselines
        StringBuffer bsql = new StringBuffer()
            .append("delete Baseline where baselineId.derivedMeasurement.id in " +
                    "(select id from ")
            .append(sql)
            .append(")");
        Query q = getSession().createQuery(bsql.toString());
        executeUpdate(map, q);

        StringBuffer dsql = new StringBuffer()
            .append("delete ")
            .append(sql);
        // delete derived measurements
        q = getSession().createQuery(dsql.toString());
        return executeUpdate(map, q);
    }

    private int executeUpdate(Map map, Query q)
    {
        int j = 0;
        for (Iterator i = map.keySet().iterator(); i.hasNext(); j++) {
            Integer appdefType = (Integer)i.next();
            List list = (List)map.get(appdefType);
            q.setInteger("appdefType"+j, appdefType.intValue())
                .setParameterList("list"+j, list);
        }
        return q.executeUpdate();
    }

    List findByInstance(int type, int id, boolean enabled) {
        String sql =
            "select distinct m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "where mt.appdefType=? and m.instanceId=? and " +
            "m.enabled = ? and m.interval is not null";

        return getSession().createQuery(sql)
            .setInteger(0, type)
            .setInteger(1, id)
            .setBoolean(2, enabled).list();
    }

    List findByInstanceForCategory(int type, int id, String cat) {
        String sql =
            "select m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "join t.category c " +
            "where mt.appdefType = ? and " +
            "m.instanceId = ? and " +
            "c.name = ? and " +
            "m.interval is not null";

        return getSession().createQuery(sql)
            .setInteger(0, type)
            .setInteger(1, id)
            .setString(2, cat).list();
    }

    List findByInstanceForCategory(int type, int id, boolean enabled,
                                   String cat) {
        String sql =
            "select m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "join t.category c " +
            "where mt.appdefType = ? and " +
            "m.instanceId = ? and " +
            "m.enabled = ? " +
            "c.name = ? and " +
            "m.interval is not null " +
            "order by t.name";

        return getSession().createQuery(sql)
            .setInteger(0, type)
            .setInteger(1, id)
            .setString(2, cat).list();
    }
    
    DerivedMeasurement findByAliasAndID(String alias,
                                        int appdefType, int appdefId) {

        String sql =
            "select distinct m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "where t.alias = ? and mt.appdefType = ? " +
            "and m.instanceId = ? and m.interval is not null";

        return (DerivedMeasurement)getSession().createQuery(sql)
            .setString(0, alias)
            .setInteger(1, appdefType)
            .setInteger(2, appdefId).uniqueResult();
    }

    List findDesignatedByInstanceForCategory(int appdefType, int iid,
                                             String cat) {
        String sql =
            "select distinct m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "where m.instanceId = ? " +
            "and t.designate = true " +
            "and mt.appdefType = ? " +
            "and t.category.name = ?";

        return getSession().createQuery(sql)
            .setInteger(0, iid)
            .setInteger(1, appdefType)
            .setString(2, cat).list();
    }

    List findDesignatedByInstance(int type, int id) {
        String sql =
            "select m from DerivedMeasurement m " +
            "join m.template t " +
            "join t.monitorableType mt " +
            "where m.instanceId = ? and " +
            "mt.appdefType = ? and " +
            "t.designate = true " +
            "order by t.name";

        return getSession().createQuery(sql)
            .setInteger(0, id)
            .setInteger(1, type).list();
    }

    List findByRawExcludeIdentity(Integer rid) {
        String sql =
            "select distinct d from DerivedMeasurement d " +
            "join d.template t " +
            "join t.measurementArgs a, " +
            "RawMeasurement r " +
            "where d.interval is not null and " +
            "d.instanceId = r.instanceId and " +
            "a.template.id = r.template.id and " +
            "r.id = ? and " +
            "t.template <> ?";

        return getSession().createQuery(sql)
                .setInteger(0, rid.intValue())
                .setString(1, "ARG1").list();
    }
}
