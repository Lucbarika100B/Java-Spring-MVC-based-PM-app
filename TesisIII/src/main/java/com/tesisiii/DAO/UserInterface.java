
package com.tesisiii.DAO;


import com.tesisiii.model.Usuario;
import java.util.List;

public interface UserInterface {
    public void addUser(Usuario u);
    public void removeUser(Usuario u);
    public Usuario getUser(Integer login);
    public void editUser (Usuario u);
    public List<Usuario> listUser();
    public List<Usuario> listAllUser();
    public List<Usuario> listManager();
    public List<Usuario> listUserDeleted();
    public Usuario getUserIdByLogin(String login);
    public List<Usuario> listUserIdByManager(Integer managerId);
    public List<Usuario> listUsuariosByPool(String managerId);
    public void mergeUser (Usuario u);
    public List<Usuario> listUserAssigned(Integer managerId, String pooPIdValor);
}
