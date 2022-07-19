package UI;

import Stomped.entity.Entity;
import Stomped.entity.EntityManager;
import UI.Event.Event;

import java.awt.*;
import java.awt.event.MouseEvent;
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

	public static boolean clickElement(MouseEvent mouseEvent) {
		boolean UIElementHit = false;

		for(int i = 0; i < UIManager.elements.size(); i++) {

			Element element = (Element) UIManager.elements.get(i);

			int mouseX = mouseEvent.getX();
			int mouseY = mouseEvent.getY() - 31;

			if(element.dimension != null && mouseX > element.point.x && mouseX < (element.point.x + element.dimension.width)
			&& mouseY > element.point.y && mouseY < (element.point.y + element.dimension.height)) {
				Event event = new Event(Event.EventType.click);
				event.setPoint(mouseEvent.getPoint());
				element.eventListener(event);
				UIElementHit = true;
			}
		}

		return UIElementHit;
	}
	
}
