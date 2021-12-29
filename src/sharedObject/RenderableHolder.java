package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> irenders;
	private Comparator<IRenderable> comparator;

	public RenderableHolder() {
		irenders = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public void add(IRenderable entity) {
		irenders.add(entity);
		Collections.sort(irenders, comparator);
	}

	public List<IRenderable> getEntities() {
		return irenders;
	}
}
