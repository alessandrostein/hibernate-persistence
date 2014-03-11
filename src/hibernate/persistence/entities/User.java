/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.persistence.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author alessandro.stein
 */
@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(name = "user.id.equals", query = "SELECT o FROM User o WHERE o.id=:id"),
    @NamedQuery(name = "user.name.equals", query = "SELECT o FROM User o WHERE o.name=:name"),
    @NamedQuery(name = "user.find.all", query = "SELECT o FROM User o"),
    @NamedQuery(name = "user.count.all", query = "SELECT COUNT(o.id) FROM User o"),
    @NamedQuery(name = "user.remove.all", query = "DELETE FROM User o"),
    @NamedQuery(name = "user.find.range", query = "SELECT o FROM User o WHERE o.id BETWEEN :minId AND :maxId"),
})

public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "User", targetEntity = Role.class, fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    private List <Role> role;
    
    /*@OneToMany(mappedBy = "User", fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Collection <Role> role;*/
    
    public User(){
        setId(0);
    }
    
    public void setId(int valor){
        this.id = valor;
    }
    
    public int getId(){
        return id;
    }
    
    public void setName(String valor){
        this.name = valor;
    }
    
    public String getName(){
        return name;
    }
            

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + '}';
    }


}
