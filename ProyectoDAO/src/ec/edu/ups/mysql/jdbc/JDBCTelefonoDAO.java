package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.modelo.telefono;

public class JDBCTelefonoDAO extends JDBCGenericDAO<telefono, Integer> implements TelefonoDAO{

	public JDBCTelefonoDAO () {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
//		conexionUno.update("DROP TABLE IF EXISTS telefono");
//		conexionUno.update("CREATE TABLE telefono (" + "ID INT NOT NULL, " + "DESCRIPTION VARCHAR(255), "
//				+ "NAME VARCHAR(255), " + "PRIMARY KEY (ID))");
		
	}

	@Override
	public void create(telefono entity) {
		// TODO Auto-generated method stub
		
		conexionUno.update("INSERT into telefono (tel_cedula, tel_numero, tel_tipo, tel_operadora) values"
				+ " ( " + entity.getId_user() + ", '"
		+ entity.getNumero() + "','"+entity.getTipo()+"','"+entity.getOperadora()+"' )");
		
	
	}
	
	
	
	
	
	
	public String cedulaUser(String nombres, String apellidos) {
		String cedula="";
		Usuario usuarioObject = null;
		ResultSet rs = conexionUno.query("SELECT * FROM Usuario WHERE nombre=" + nombres + "AND apellido="+apellidos);
		try {
			if (rs != null && rs.next()) {
				
				usuarioObject = new Usuario (rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("correo"), rs.getString("contrasena"));
				cedula = usuarioObject.getCedula();
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCUsuarioDAO:read): " + e.getMessage());
		}

		return cedula;
		
	}
	

	@Override
	public telefono read(Integer id) {
		// TODO Auto-generated method stub
		System.out.print("ID: "+id);
		
		telefono telefonoObject = null;
		ResultSet rs = conexionUno.query("SELECT * FROM telefono WHERE tel_codigo=" + id);
		try {
			if (rs != null && rs.next()) {
				telefonoObject = new telefono (rs.getInt("tel_codigo"), rs.getString("tel_cedula"), rs.getString("tel_numero"),rs.getString("tel_tipo"), rs.getString("tel_operadora"));
			}
		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCTelefonoDAO:read): " + e.getMessage());
		}

		return telefonoObject;
		
		
	}

	@Override
	public void update(telefono entity) {
		// TODO Auto-generated method stub
		conexionUno.update("UPDATE telefono SET tel_cedula = '" + entity.getId_user()+ "', tel_numero  = '"
				+ entity.getNumero() + "',tel_tipo ='"+entity.getTipo()+ "',tel_operadora  ='"+entity.getOperadora()+    "' WHERE tel_codigo = " + entity.getTelf_id());
		
	}

	@Override
	public void delete(telefono entity) {
		// TODO Auto-generated method stub
		conexionUno.update("DELETE FROM telefono WHERE tel_codigo = " + entity.getTelf_id());
		
	}

	@Override
	public List<telefono> find() {
		List<telefono> list = new ArrayList<telefono>();
		ResultSet rs = conexionUno.query("SELECT * FROM telefono");
		try {
			while (rs.next()) {
				list.add(new telefono(rs.getInt("tel_codigo"), rs.getString("tel_cedula"), rs.getString("tel_numero"),rs.getString("tel_tipo"), rs.getString("tel_operadora")));
			}

		} catch (SQLException e) {
			System.out.println(">>>WARNING (JDBCTelefonoDAO:find): " + e.getMessage());
		}
//		for(telefono str : list)
//		{
//		    System.out.println(str.telf_id +','+ str.id_user +','+str.numero+','+str.tipo+','+str.operadora);
//		}
		return list;
	}

	@Override
	public int buscar(String email, String contrasena) {
		// TODO Auto-generated method stub
		return 0;
	}

}
