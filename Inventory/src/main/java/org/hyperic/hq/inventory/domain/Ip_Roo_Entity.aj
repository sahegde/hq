// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.hyperic.hq.inventory.domain;

import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.hyperic.hq.inventory.domain.Ip;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Ip_Roo_Entity {
    
    declare @type: Ip: @Entity;
    
    @PersistenceContext
    transient EntityManager Ip.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Ip.id;
    
    @Version
    @Column(name = "version")
    private Integer Ip.version;
    
    public Long Ip.getId() {
        return this.id;
    }
    
    public void Ip.setId(Long id) {
        this.id = id;
    }
    
    public Integer Ip.getVersion() {
        return this.version;
    }
    
    public void Ip.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Ip.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Ip.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Ip attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Ip.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Ip Ip.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Ip merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Ip.entityManager() {
        EntityManager em = new Ip().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Ip.countIps() {
        return entityManager().createQuery("select count(o) from Ip o", Long.class).getSingleResult();
    }
    
    public static List<Ip> Ip.findAllIps() {
        return entityManager().createQuery("select o from Ip o", Ip.class).getResultList();
    }
    
    public static Ip Ip.findIp(Long id) {
        if (id == null) return null;
        return entityManager().find(Ip.class, id);
    }
    
    public static List<Ip> Ip.findIpEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Ip o", Ip.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
