package de.samdev.absgdx.framework.menu.agdxml;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;

import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;

public class AgdxmlGridDefinitions {

	public final List<AgdxmlGridDefinitionsValue> rows;
	public final List<AgdxmlGridDefinitionsValue> columns;
	
	public AgdxmlGridDefinitions(List<AgdxmlGridDefinitionsValue> rows, List<AgdxmlGridDefinitionsValue> columns) {
		super();
		
		this.rows = rows;
		this.columns = columns;
	}

	public Rectangle getBoundaries(int grid_x, int grid_y, Rectangle boundaries) throws AgdxmlParsingException {
		if (grid_x < 0 || grid_x >= columns.size()) throw new AgdxmlParsingException("Invalid column value: " + grid_x);
		if (grid_y < 0 || grid_y >= rows.size()) throw new AgdxmlParsingException("Invalid row value: " + grid_y);
		
		int weightsum_x = 0;
		int weightsum_y = 0;
		
		float usedspacesum_x = 0;
		float usedspacesum_y = 0;
		
		for (AgdxmlGridDefinitionsValue def_col : columns) {
			if (def_col.unit == AgdxmlGridDefinitionsUnit.WEIGHT)
				weightsum_x += def_col.value;
			else if (def_col.unit == AgdxmlGridDefinitionsUnit.PIXEL)
				usedspacesum_x += def_col.value;
			else if (def_col.unit == AgdxmlGridDefinitionsUnit.PERCENTAGE)
				usedspacesum_x += (def_col.value/100f) * boundaries.width;
		}
		
		for (AgdxmlGridDefinitionsValue def_row : rows) {
			if (def_row.unit == AgdxmlGridDefinitionsUnit.WEIGHT)
				weightsum_y += def_row.value;
			else if (def_row.unit == AgdxmlGridDefinitionsUnit.PIXEL)
				usedspacesum_y += def_row.value;
			else if (def_row.unit == AgdxmlGridDefinitionsUnit.PERCENTAGE)
				usedspacesum_y += (def_row.value/100f) * boundaries.height;
		}
		
		float px = 0;
		float pwidth = 0;
		for (int i = 0; i <= grid_x; i++) {
			px += pwidth;
			
			switch (columns.get(i).unit) {
			case PIXEL:
				pwidth = columns.get(i).value;
				break;
			case PERCENTAGE:
				pwidth = (columns.get(i).value / 100f) * boundaries.width;
				break;
			case WEIGHT:
				pwidth = (columns.get(i).value * 1f / weightsum_x) * (boundaries.width - usedspacesum_x);
				break;
			default:
				break;
			}
		}
		
		float py = 0;
		float pheight = 0;
		for (int i = 0; i <= grid_y; i++) {
			py += pheight;
			
			switch (rows.get(i).unit) {
			case PIXEL:
				pheight = rows.get(i).value;
				break;
			case PERCENTAGE:
				pheight = (rows.get(i).value / 100f) * boundaries.height;
				break;
			case WEIGHT:
				pheight = (rows.get(i).value * 1f / weightsum_y) * (boundaries.height - usedspacesum_y);
				break;
			default:
				break;
			}
		}
		
		if (px > (boundaries.x + boundaries.width)) px = (boundaries.x + boundaries.width);
		if (px + pwidth > (boundaries.x + boundaries.width)) pwidth = (boundaries.x + boundaries.width) - px;

		if (py > (boundaries.y + boundaries.height)) py = (boundaries.y + boundaries.height);
		if (py + pheight > (boundaries.y + boundaries.height)) pheight = (boundaries.y + boundaries.height) - py;
		
		return new Rectangle(px, py, pwidth, pheight);
	}

}
