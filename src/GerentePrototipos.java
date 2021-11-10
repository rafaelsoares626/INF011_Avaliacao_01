
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GerentePrototipos {
	
	private Map<String, PrototipavelIF> catalogo;	
	
	public GerentePrototipos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.catalogo = new HashMap<String, PrototipavelIF>();		
	}

	public void catalogar(String nome, PrototipavelIF curso) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		this.catalogo.put(nome, curso.prototipar());		
	}
	
	public static GerentePrototipos getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return new GerentePrototipos();
	}
	
	public Curso getCurso(String chave) {
		return (Curso) catalogo.get(chave);
	}
}

