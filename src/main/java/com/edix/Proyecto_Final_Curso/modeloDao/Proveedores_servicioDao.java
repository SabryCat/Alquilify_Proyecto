package com.edix.Proyecto_Final_Curso.modeloDao;

import java.util.List;


import com.edix.Proyecto_Final_Curso.entities.ProveedoresServicio;

public interface Proveedores_servicioDao {
	
	List<ProveedoresServicio> buscarTodos();
	ProveedoresServicio buscarProveedorServicio(int idProveedor);
	ProveedoresServicio altaProveedor(ProveedoresServicio proveedor);
}
