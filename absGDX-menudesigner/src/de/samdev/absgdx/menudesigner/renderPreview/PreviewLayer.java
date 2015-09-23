package de.samdev.absgdx.menudesigner.renderPreview;

import java.io.File;
import java.io.PrintWriter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.renderer.DebugTextRenderer;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class PreviewLayer extends AgdxmlLayer {

	private FileHandle tex;
	private String texdef;
	private BitmapFont fnt;
	
	public PreviewLayer(PreviewGame owner, String agdxml, String agdtexdef, FileHandle texture) throws AgdxmlParsingException {
		super(owner, null, agdxml);
		
		this.tex = texture;
		this.texdef = agdtexdef;
	}

	@Override
	public void initialize() {
		try {
			fnt = new BitmapFont(Gdx.files.absolute("F:\\Eigene Dateien\\Dropbox\\Programming\\Java\\workspace\\absGDX\\android\\assets\\consolefont.fnt")); //TODO move to resources (then copy to temp ?)
			
			File tmp = File.createTempFile(Math.random() + "_absgdx_menudesigner_" + System.currentTimeMillis(), ".agdtexdef");
			
			PrintWriter out = new PrintWriter(tmp);
			out.write(texdef);
			out.close();
			
			loadGuiTextureProviderFromTextureDefinition(Gdx.files.absolute(tmp.getAbsolutePath()), new Texture(tex));
			
			tmp.delete();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, DebugTextRenderer tRenderer) {
		if (fnt == null) initialize();
		
		srenderer.identity();
		sbatch.getTransformMatrix().idt();
		
		srenderer.translate(0, owner.getScreenHeight(), 0);
		srenderer.scale(1, -1, 1);

		sbatch.getTransformMatrix().translate(0, owner.getScreenHeight(), 0);
		sbatch.getTransformMatrix().scale(1, -1, 1);
		
		sbatch.enableBlending();
		srenderer.setAutoShapeType(true);
		
		getMenuRoot().renderElement(sbatch, srenderer, fnt, this);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Method getDeclaredMethod(String name, Class... parameterTypes) throws ReflectionException {
		if (parameterTypes.length == 0) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP");
		if (parameterTypes.length == 1) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP", Object.class);
		if (parameterTypes.length == 2) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP", Object.class, Object.class);
		if (parameterTypes.length == 3) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP", Object.class, Object.class, Object.class);
		if (parameterTypes.length == 4) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP", Object.class, Object.class, Object.class, Object.class);
		if (parameterTypes.length == 5) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP", Object.class, Object.class, Object.class, Object.class, Object.class);
		if (parameterTypes.length == 6) return ClassReflection.getDeclaredMethod(this.getClass(), "NOP", Object.class, Object.class, Object.class, Object.class, Object.class, Object.class);
		
		return ClassReflection.getDeclaredMethod(this.getClass(), "NOP");
	}

	public void NOP() { /* - */ }

	@SuppressWarnings("unused")
	public void NOP(Object a) { /* - */ }

	@SuppressWarnings("unused")
	public void NOP(Object a, Object b) { /* - */ }

	@SuppressWarnings("unused")
	public void NOP(Object a, Object b, Object c) { /* - */ }

	@SuppressWarnings("unused")
	public void NOP(Object a, Object b, Object c, Object d) { /* - */ }

	@SuppressWarnings("unused")
	public void NOP(Object a, Object b, Object c, Object d, Object e) { /* - */ }

	@SuppressWarnings("unused")
	public void NOP(Object a, Object b, Object c, Object d, Object e, Object f) { /* - */ }
}
