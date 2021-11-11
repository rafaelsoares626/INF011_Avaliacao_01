
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GerentePrototipos {
	
	private static GerentePrototipos catalogo;	
	private Map<String, PrototipavelIF> registros;	
	
	public GerentePrototipos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.registros = new HashMap<String, PrototipavelIF>();		
	}

	public void catalogar(String nome, PrototipavelIF curso) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		this.registros.put(nome, curso.prototipar());		
	}
	
	public static GerentePrototipos getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(GerentePrototipos.catalogo == null) {
			GerentePrototipos.catalogo = new GerentePrototipos();
		}
		return GerentePrototipos.catalogo; 
	}
	
	public Curso getCurso(String chave) {
		return (Curso) registros.get(chave);
	}
}

