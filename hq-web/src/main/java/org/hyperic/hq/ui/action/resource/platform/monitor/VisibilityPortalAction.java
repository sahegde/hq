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

package org.hyperic.hq.ui.action.resource.platform.monitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hyperic.hq.bizapp.shared.AppdefBoss;
import org.hyperic.hq.bizapp.shared.AuthzBoss;
import org.hyperic.hq.bizapp.shared.ControlBoss;
import org.hyperic.hq.ui.Constants;
import org.hyperic.hq.ui.Portal;
import org.hyperic.hq.ui.action.resource.common.monitor.visibility.ResourceVisibilityPortalAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A <code>BaseDispatchAction</code> that sets up platform monitor portals.
 */
public class VisibilityPortalAction
    extends ResourceVisibilityPortalAction {

    private static final String TITLE_CURRENT_HEALTH = "resource.platform.monitor.visibility.CurrentHealthTitle";

    private static final String PORTLET_CURRENT_HEALTH = ".resource.platform.monitor.visibility.CurrentHealth";

    private static final String TITLE_PLATFORM_METRICS = "resource.platform.monitor.visibility.PlatformMetricsTitle";

    private static final String PORTLET_PLATFORM_METRICS = ".resource.platform.monitor.visibility.PlatformMetrics";

    private static final String TITLE_PERFORMANCE = "resource.platform.monitor.visibility.PerformanceTitle";

    private static final String PORTLET_PERFORMANCE = ".resource.platform.monitor.visibility.Performance";

    @Autowired
    public VisibilityPortalAction(AppdefBoss appdefBoss, AuthzBoss authzBoss, ControlBoss controlBoss) {
        super(appdefBoss, authzBoss, controlBoss);
    }

    public ActionForward currentHealth(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        setResource(request);

        super.currentHealth(mapping, form, request, response);

        Portal portal = Portal.createPortal(TITLE_CURRENT_HEALTH, PORTLET_CURRENT_HEALTH);
        request.setAttribute(Constants.PORTAL_KEY, portal);
        return null;
    }

    public ActionForward resourceMetrics(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        setResource(request);

        super.resourceMetrics(mapping, form, request, response);

        Portal portal = Portal.createPortal(TITLE_PLATFORM_METRICS, PORTLET_PLATFORM_METRICS);
        request.setAttribute(Constants.PORTAL_KEY, portal);
        return null;
    }

    public ActionForward performance(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        setResource(request);

        Portal portal = Portal.createPortal(TITLE_PERFORMANCE, PORTLET_PERFORMANCE);
        request.setAttribute(Constants.PORTAL_KEY, portal);

        return null;
    }
}
