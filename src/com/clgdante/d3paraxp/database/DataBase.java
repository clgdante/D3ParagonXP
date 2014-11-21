package com.clgdante.d3paraxp.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	private List<DBElement> elems;

	public DataBase() {
		loadDB();
	}

	private void loadDB() {
		elems = new ArrayList<DBElement>();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("db.txt");
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String text = null;
			while ((text = reader.readLine()) != null) {
				elems.add(parseDBElem(text));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
	}

	private DBElement parseDBElem(String text) {
		String[] arr = text.split("\t\t");
		String pLvl = arr[0];
		String totalExp = arr[1];

		int i_pLvl = Integer.parseInt(pLvl);
		// TODO better comma replacement (via NumberFormat or Locale)
		long l_totalExp = Long.parseLong(totalExp.replaceAll(",", ""));

		return new DBElement(i_pLvl, l_totalExp);
	}

	/**
	 * Returns TotalExp for given pLvl
	 * 
	 * @param pLvl
	 * @return long TotalExp or -1 if pLvl not found
	 */
	public long lookupTotalExp(int pLvl) {
		for (int i = 0; i < elems.size(); i++) {
			DBElement tmp = elems.get(i);
			if (tmp.getPlvl() == pLvl) {
				return tmp.getTotalExp();
			}
		}
		return -1;
	}

	/**
	 * Returns pLvl that is mapped to <= given TotalExp
	 * 
	 * @param totalExp
	 * @return int pLvl that is valid for the TotalExp or -1 if TotalExp not found
	 */
	public int lookupPLvl(long totalExp) {
		for (int i = 0; i < elems.size(); i++) {
			DBElement tmp = elems.get(i);
			if (tmp.getTotalExp() == totalExp) {
				return tmp.getPlvl();
			} else if (tmp.getTotalExp() > totalExp) {
				return elems.get(i - 1).getPlvl();
			}
		}
		return -1;
	}
}
