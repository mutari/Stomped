package UI;

import Stomped.entity.Entity;
import Stomped.entity.EntityManager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class UIManager {
	
	private static ArrayList<Element> elements = new ArrayList<>();
	
	public static void add(Element element) {
		UIManager.elements.add(element);
	}

	public static int getSize() {
		return UIManager.elements.size();
	}

	public static void removeById(String id) {
		Iterator<Element> itr = UIManager.elements.iterator();
		while (itr.hasNext()) {
			Element b = itr.next(); // must be called before you can call i.remove()
			if(b.elementId.equals(id))
				itr.remove();
		}
	}

	public static Element getElementById(String id) {
		for(int i = 0; i < UIManager.elements.size(); i++) {
			if(UIManager.elements.get(i).getId().equals(id)) {
				return UIManager.elements.get(i);
			}
		}
		return null;
	}
	
	public static void draw(Graphics2D g) {
		for(int i = 0; i < UIManager.elements.size(); i++) {
			UIManager.elements.get(i).draw(g);
			//g.drawRect(this.elements.get(i).getPoint().x, this.elements.get(i).getPoint().y, this.elements.get(i).getDimension().width, this.elements.get(i).getDimension().height);
		}
	}
	
}
