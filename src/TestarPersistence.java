import hibernate.persistence.dao.RoleDAO;
import java.util.ArrayList;
import hibernate.persistence.entities.User;
import hibernate.persistence.dao.UserDAO;
import hibernate.persistence.entities.Role;
import java.util.HashSet;
import java.util.Set;

public class TestarPersistence {
 
    public static void main(String[] args) {
        try {
            createUsers();
            showAllUser();
        } catch (Exception ex) {
            System.out.println("A tentativa de criar usuários falhou!/n... " + ex.getMessage());
        }
        
    }
    
    private static void createUsers() throws Exception {
        System.out.println("Criando usuários...");
        
        RoleDAO roledao = new RoleDAO();
        Role role1 = (Role) roledao.getNewInstance();
        Role role2 = (Role) roledao.getNewInstance();
        
        role1.setName("Regra 1");
        roledao.save(role1);
        
        role2.setName("Regra 2");
        roledao.save(role2);
        
        UserDAO dao = new UserDAO();
        User admin = (User) dao.getNewInstance();
        admin.setName("Administrador");

        User guest = (User) dao.getNewInstance();
        guest.setName("Convidado");
        
        User user = (User) dao.getNewInstance();
        user.setName("Ayrton Senna");

        Set roles = new HashSet();
        roles.add(role1);
        roles.add(role2);
        
        user.setRole(roles);
        admin.setRole(roles);
        //guest.setRole(roles);
        
         dao.save(user);
        dao.save(admin);
        dao.save(guest);
       

        System.out.print(" OK!");

    }    
    
    private static void showAllUser() throws Exception { 
        System.out.println("Listando usuários...");
        UserDAO dao = new UserDAO();
        ArrayList users = (ArrayList) dao.findAll();
        User o;
        
        for (int i=0; i<users.size(); i++) {
            o = (User) users.get(i);
            System.out.println(o);
        }
        
        
        User admin = (User) dao.getNewInstance();    
    
    
    }
    
    
}
