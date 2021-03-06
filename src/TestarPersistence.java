
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
            long tempoInit = System.currentTimeMillis();

            // Remove todas as regras;
            removeAllRoles();
            // Remove todos os usuarios;
            removeAllUser();
            // Cria um novo usuario.
            User user = createUsers("User 1");
            // Cria regras
            Role role1 = createRoles("Regra 1");
            Role role2 = createRoles("Regra 2");
            // Adiciona regras em um HasSet
            Set role = new HashSet();
            role.add(role1);
            role.add(role2);
            // Cria o relacionamento entre user e as regras
            createUsersRoles(role, user);
            // Lista todos usuarios
            showAllUser();
            // Lista todas as regras
            showAllRole();
            // Lista os relacionamentos de usuarios e regras
            showAllUserRole();
            // Busca um usuario que contenha uma regra
            findUser(role1);
            // Adiciona uma nova regra ao usuario.
            Role role3 = createRoles("Regra 3 - add new Role");
            addNewRole(user, role3);
            // Verifica se usuario possui determinada regra.
            hasRole(user, role2);
            // Busca as regras que o usuario possui.
            findRoles(user);
            // Remove uma regra do usuario.
            removeRole(role1, user);
            
            long tempoFinish = System.currentTimeMillis();
            System.out.println("Tempo de execução = " + (tempoFinish - tempoInit) / 1000.0 + "s");
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
        System.out.println("Criando relacionamento regras e usuarios...");
        UserDAO dao = new UserDAO();

        user1.setRole(role1);
        dao.update(user1);
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

    private static void showAllUserRole() throws Exception {
        System.out.println("Listando usuários e regras ...");

        UserDAO dao_user = new UserDAO();
        ArrayList users = (ArrayList) dao_user.findAll();
        User user;

        for (Object u : users) {
            user = (User) u;
            System.out.println(user.toString());

            Set userrole = user.getRole();
            Role role;

            for (Object r : userrole) {
                role = (Role) r;
                System.out.println(role);
            }
        }

    }

    private static void findUser(Role role) throws Exception {
        System.out.println("Listando usuários que contenham a regra " + role.getName() + "  ...");

        RoleDAO dao = new RoleDAO();

        Set userrole = dao.findUser(role);
        User o;

        for (Object u : userrole) {
            o = (User) u;
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
        Set role = dao.findRole(user);
        Role o;

        for (Object u : role) {
            o = (Role) u;
            System.out.println(o);
        }
    }

    private static void removeAllUser() throws Exception {
        System.out.println("Romovendo todos os usuarios...");
        UserDAO dao = new UserDAO();
        dao.removeAll();
    }

    private static void removeAllRoles() throws Exception {
        System.out.println("Removendo todas as roles...");
        RoleDAO dao = new RoleDAO();
        dao.removeAll();
    }

}
