package de.samdev.absgdx.menudesigner.renderPreview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

import com.badlogic.gdx.Gdx;
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
	public PreviewLayer(PreviewGame owner, String agdxml) throws AgdxmlParsingException {
		super(owner, null, agdxml);
	}

	@Override
	public void initialize() {
		try {
			File tmp = File.createTempFile(Math.random() + "_absgdx_menudesigner_" + System.currentTimeMillis(), "_.agdtexdef");
			
			PrintWriter out = new PrintWriter(tmp);
			out.write(((PreviewGame)owner).texdef);
			out.close();
			
			loadGuiTextureProviderFromTextureDefinition(Gdx.files.absolute(tmp.getAbsolutePath()), new Texture(((PreviewGame)owner).tex));
			
			tmp.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer, DebugTextRenderer tRenderer) {
		if (((PreviewGame)owner).fnt == null) {
			try {
				File tmp0 = File.createTempFile("__000_absgdx_menudesigner_font_" + System.currentTimeMillis(), "_agdx_fnt");
				tmp0.delete();
				tmp0.mkdir();
				File tmp1 = new File(tmp0, "consolefont.fnt");
				File tmp2 = new File(tmp0, "consolefont.png");

				writeEmbeddedResourceToLocalFile("consolefont.fnt", tmp1);
				writeEmbeddedResourceToLocalFile("consolefont.png", tmp2);
				
				((PreviewGame)owner).fnt = new BitmapFont(Gdx.files.absolute(tmp1.getAbsolutePath()));

				tmp1.delete();
				tmp2.delete();
				tmp0.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		srenderer.identity();
		sbatch.getTransformMatrix().idt();
		
		srenderer.translate(0, owner.getScreenHeight(), 0);
		srenderer.scale(1, -1, 1);

		sbatch.getTransformMatrix().translate(0, owner.getScreenHeight(), 0);
		sbatch.getTransformMatrix().scale(1, -1, 1);
		
		sbatch.enableBlending();
		srenderer.setAutoShapeType(true);
		
		getMenuRoot().renderElement(sbatch, srenderer, ((PreviewGame)owner).fnt, this);
	}
	
	public void writeEmbeddedResourceToLocalFile(final String resourceName, final File configFile) throws IOException {
		final URL resourceUrl = getClass().getClassLoader().getResource( resourceName );

		byte[] buffer = new byte[1024];
		int byteCount = 0;

		InputStream inputStream = null;
		OutputStream outputStream = null;

		inputStream = resourceUrl.openStream();
		outputStream = new FileOutputStream(configFile);

		while((byteCount = inputStream.read(buffer)) >= 0) {
			outputStream.write(buffer, 0, byteCount);
		}
		
	    inputStream.close();
	    outputStream.flush();
	    outputStream.close();
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
