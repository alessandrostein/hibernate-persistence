/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author alessandro.stein
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "user.id.equals", query = "SELECT o FROM User o WHERE o.id=:id"),
    @NamedQuery(name = "user.name.equals", query = "SELECT o FROM User o WHERE o.name=:name"),
    @NamedQuery(name = "user.find.all", query = "SELECT o FROM User o"),
    //@NamedQuery(name = "user.has.role", query = "SELECT o FROM User o inner join o.roles u WHERE u.id = :roleid and o.id = :userid" ),
    //@NamedQuery(name = "user.find.role", query = "SELECT o FROM User o inner join o.roles u WHERE u.id = :id" ),
    @NamedQuery(name = "user.count.all", query = "SELECT COUNT(o.id) FROM User o"),
    @NamedQuery(name = "user.remove.all", query = "DELETE FROM User"),// o inner join o.roles u WHERE u.id = :roleid and o.id = :userid"),
    @NamedQuery(name = "user.remove.role", query = "DELETE FROM User o"),
    @NamedQuery(name = "user.find.range", query = "SELECT o FROM User o WHERE o.id BETWEEN :minId AND :maxId"),})

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userrole", joinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "roleid", referencedColumnName = "id")})
    private Set<Role> roles;

    public Set getRole() {
        return roles;
    }

    public void setRole(Set role) {
        this.roles = role;
    }

    public User() {
        setId(0);
    }

    public void setId(int valor) {
        this.id = valor;
    }

    public int getId() {
        return id;
    }

    public void setName(String valor) {
        this.name = valor;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + '}';
    }

}
