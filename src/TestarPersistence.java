
import hibernate.persistence.dao.RoleDAO;
import java.util.ArrayList;
import hibernate.persistence.entities.User;
import hibernate.persistence.dao.UserDAO;
import hibernate.persistence.entities.Role;
import java.util.Set;

public class TestarPersistence {

    public static void main(String[] args) {
        try {
            User user = createUsers("User 1");
            Role role = createRoles("Regra 1");
            
            createUsersRoles((Set) role, user);
            
            showAllUser();
            showAllRole();
            showAllUserRole(user);
            
            findUser(role);
            
            addNewRole(user, role);
            
            removeRole(role, user);
            
            hasRole(user, role);
            
            findRoles(user);
        } catch (Exception ex) {
            System.out.println("A tentativa de criar usuários falhou!/n... " + ex.getMessage());
        }

    }

    private static User createUsers(String name) throws Exception {
        UserDAO dao_user = new UserDAO();     

        System.out.println("Cria novos usuarios...");

        User user1 = new User();
        user1.setName(name);
        dao_user.save(user1);
        
        return user1;
    }
    
    private static Role createRoles(String name) throws Exception {
        RoleDAO dao_role = new RoleDAO();
        
        System.out.println("Cria novas regras...");
        
        Role role1 = new Role();
        role1.setName(name);
        dao_role.save(role1);
        
        return role1;
        
    }
    
    private static void createUsersRoles(Set role1, User user1) throws Exception {
        UserDAO dao = new UserDAO();
        
        user1.setRole(role1);
        dao.save(user1);
        
        
        
        /*UserRoleDAO dao_userrole = new UserRoleDAO();
        
        System.out.println("Criando relacionamento...");
        UserRole userrole1 = new UserRole();
        userrole1.setRoleid(role1.getID());
        userrole1.setUserid(user1.getId());
        dao_userrole.save(userrole1);*/
        
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

    }

    private static void showAllRole() throws Exception {
        System.out.println("Listando regras...");
        RoleDAO dao = new RoleDAO();
        ArrayList roles = (ArrayList) dao.findAll();
        Role o;

        for (int i = 0; i < roles.size(); i++) {
            o = (Role) roles.get(i);
            System.out.println(o);
        }

    }

    private static void showAllUserRole(User user) throws Exception {
        System.out.println("Listando usuários e regras ...");
        
        ArrayList userrole = (ArrayList) user.getRole();
        Role role;
        
        for (int i = 0; i < userrole.size(); i++){
            role = (Role) userrole.get(i);
            System.out.println(role);
        }
    }

    private static void findUser(Role role) throws Exception {
        System.out.println("Listando usuários que contenham a regra " + role.getName() + "  ...");
        
        RoleDAO dao = new RoleDAO();

        ArrayList userrole = (ArrayList) dao.findUser(role);
        User o;

        for (int i = 0; i < userrole.size(); i++) {
            o = (User) userrole.get(i);
            System.out.println(o);
        }

    }
    
    private static void addNewRole(User user, Role role) throws Exception {
        System.out.println("Adicionando regras a usuários...");
        UserDAO dao = new UserDAO();
        dao.addRole(user, role);
    }
    
    private static void removeRole(Role role, User user) throws Exception {
        System.out.println("Removendo regras...");
        UserDAO dao = new UserDAO();
        dao.removeRole(user, role);
    }
    
    private static boolean hasRole(User user, Role role) throws Exception {
        System.out.println("Verificando regras...");
        UserDAO dao = new UserDAO();
        return dao.hasRole(user, role);
    }

    private static void findRoles(User user) throws Exception {
        System.out.println("Listando Regras dos usuários...");
        UserDAO dao = new UserDAO();
        ArrayList role = (ArrayList) dao.findRole(user);
        Role o;

        for (int i = 0; i < role.size(); i++) {
            o = (Role) role.get(i);
            System.out.println(o);
        }
    }
    
}
