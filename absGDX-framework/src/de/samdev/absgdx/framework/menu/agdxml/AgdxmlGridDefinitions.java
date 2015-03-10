package de.samdev.absgdx.framework.menu.agdxml;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;

import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

/**
 * This class holds a set of Row/Column definitions (from an AGDXML grid tag)
 *
 * this class is immutable
 *
 */
public class AgdxmlGridDefinitions {

	/** the row definitions */
	public final List<AgdxmlValue> rows;
	/** the column definitions */
	public final List<AgdxmlValue> columns;
	
	/**
	 * Create a new AgdxmlGridDefinitions instance
	 * 
	 * @param rows the row definitions
	 * @param columns the column definitions
	 */
	public AgdxmlGridDefinitions(List<AgdxmlValue> rows, List<AgdxmlValue> columns) {
		super();
		
		this.rows = rows;
		this.columns = columns;
	}

	/**
	 * Create a new (single row - single column) AgdxmlGridDefinitions instance
	 */
	public AgdxmlGridDefinitions() {
		super();
		
		this.rows = new ArrayList<AgdxmlValue>();
		rows.add(new AgdxmlValue(1f, AgdxmlValueUnit.WEIGHT));

		this.columns = new ArrayList<AgdxmlValue>();
		columns.add(new AgdxmlValue(1f, AgdxmlValueUnit.WEIGHT));
	}

	/**
	 * Get the boundaries of a a single area
	 * 
	 * @param grid_x the grid column
	 * @param grid_y the grid row
	 * @param boundaries the parent boundaries (of the grid that holds these definitions)
	 * @return the boundaries of the specific area
	 * @throws AgdxmlParsingException if there is an problem with the definitions
	 */
	public Rectangle getBoundaries(int grid_x, int grid_y, Rectangle boundaries) throws AgdxmlParsingException {
		if (grid_x < 0 || grid_x >= columns.size()) throw new AgdxmlParsingException("Invalid column value: " + grid_x);
		if (grid_y < 0 || grid_y >= rows.size()) throw new AgdxmlParsingException("Invalid row value: " + grid_y);
		
		int weightsum_x = 0;
		int weightsum_y = 0;
		
		float usedspacesum_x = 0;
		float usedspacesum_y = 0;
		
		for (AgdxmlValue def_col : columns) {
			if (def_col.unit == AgdxmlValueUnit.WEIGHT)
				weightsum_x += def_col.value;
			else if (def_col.unit == AgdxmlValueUnit.PIXEL)
				usedspacesum_x += def_col.value;
			else if (def_col.unit == AgdxmlValueUnit.PERCENTAGE)
				usedspacesum_x += (def_col.value/100f) * boundaries.width;
		}
		
		for (AgdxmlValue def_row : rows) {
			if (def_row.unit == AgdxmlValueUnit.WEIGHT)
				weightsum_y += def_row.value;
			else if (def_row.unit == AgdxmlValueUnit.PIXEL)
				usedspacesum_y += def_row.value;
			else if (def_row.unit == AgdxmlValueUnit.PERCENTAGE)
				usedspacesum_y += (def_row.value/100f) * boundaries.height;
		}
		
		float px = 0;
		float pwidth = 0;
		for (int i = 0; i <= grid_x; i++) {
			px += pwidth;
			
			pwidth = columns.get(i).get(boundaries.width, weightsum_x, boundaries.width - usedspacesum_x, true);
		}
		
		float py = 0;
		float pheight = 0;
		for (int i = 0; i <= grid_y; i++) {
			py += pheight;

			pheight = rows.get(i).get(boundaries.height, weightsum_y, boundaries.height - usedspacesum_y, true);
		}
		
		if (px > (boundaries.x + boundaries.width)) px = (boundaries.x + boundaries.width);
		if (px + pwidth > (boundaries.x + boundaries.width)) pwidth = (boundaries.x + boundaries.width) - px;

		if (py > (boundaries.y + boundaries.height)) py = (boundaries.y + boundaries.height);
		if (py + pheight > (boundaries.y + boundaries.height)) pheight = (boundaries.y + boundaries.height) - py;
		
		return new Rectangle(px, py, pwidth, pheight);
	}

}
