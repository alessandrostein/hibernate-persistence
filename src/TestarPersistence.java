
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
            createUsersRoles();
            showAllUser();
        } catch (Exception ex) {
            System.out.println("A tentativa de criar usuários falhou!/n... " + ex.getMessage());
        }

    }

    private static void createUsersRoles() throws Exception {
        System.out.println("Criando regras...");

        RoleDAO roledao = new RoleDAO();

        roledao.removeAll();

        Role role1 = (Role) roledao.getNewInstance();
        role1.setName("Role 1");

        Role role2 = (Role) roledao.getNewInstance();
        role2.setName("Role 2");

        roledao.save(role1);
        roledao.save(role2);

        System.out.println("Criando usuários...");

        UserDAO dao = new UserDAO();
        dao.removeAll();

        User admin = (User) dao.getNewInstance();
        admin.setName("Administrador");

        User guest = (User) dao.getNewInstance();
        guest.setName("Convidado");

        User user = (User) dao.getNewInstance();
        user.setName("Ayrton Senna");

        System.out.println("Relacionando usuários e regras...");

        Set roles1 = new HashSet();
        roles1.add(role1);
        roles1.add(role2);
        
        Set roles2 = new HashSet();
        roles2.add(role1);

        user.setRole(roles1);
        admin.setRole(roles2);
        guest.setRole(roles1);

        dao.save(user);
        showAllUser();
        dao.save(admin);
        showAllUser();
        dao.save(guest);
        showAllUser();
        
        showUserRole(role1);
        
        System.out.print("Adicionando uma nova regra ao usuario");
        dao.addRole(admin, role1);
        showUserRole(role1);
        
        if (dao.hasRole(user, role2)){
             System.out.print("O usuario " + user.getName() + "possui a regra " + role2.getName());
        }else{
            System.out.print("O usuario " + user.getName() + "NÃO possui a regra " + role2.getName()); 
        }

        System.out.print(" OK!");

    }

    public static void showUserRole(Role role) throws Exception {
        System.out.println("Listando regras do usuario...");
        
        RoleDAO dao = new RoleDAO();
        ArrayList roles = (ArrayList) dao.findUser(role);
        User o;

        for (int i = 0; i < roles.size(); i++) {
            o = (User) roles.get(i);
            System.out.println(o);
        }


    }

    private static void showAllUser() throws Exception {
        System.out.println("Listando usuários...");
        UserDAO dao = new UserDAO();
        ArrayList users = (ArrayList) dao.findAll();
        User o;

        for (int i = 0; i < users.size(); i++) {
            o = (User) users.get(i);
            System.out.println(o);
        }

        User admin = (User) dao.getNewInstance();

    }

}
