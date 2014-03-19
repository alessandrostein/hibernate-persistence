/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alessandro.stein
 */
@Entity
@Table(name = "role")

@NamedQueries({
    @NamedQuery(name = "role.id.equals", query = "SELECT o FROM Role o WHERE o.id=:id"),
    @NamedQuery(name = "role.name.equals", query = "SELECT o FROM Role o WHERE o.name=:name"),
    @NamedQuery(name = "role.find.all", query = "SELECT o FROM Role o"),
    @NamedQuery(name = "role.count.all", query = "SELECT COUNT(o.id) FROM Role o"),
    @NamedQuery(name = "role.remove.all", query = "DELETE FROM Role o"),
    @NamedQuery(name = "role.find.range", query = "SELECT o FROM Role o WHERE o.id BETWEEN :minId AND :maxId")
})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> user;

    public Set<User> getUser() {
        return this.user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public void setID(int valor) {
        this.id = valor;
    }

    public int getID() {
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
        return "Role{" + "id=" + id + ", name=" + name + '}';
    }

}
