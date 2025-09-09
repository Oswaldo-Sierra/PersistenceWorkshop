package com.edu.uptc.PersistenceWorkshop.Interface;

import com.edu.uptc.PersistenceWorkshop.enums.ETypeFiLe;

public interface IActionsFile {
	public void dumpFile(ETypeFiLe eTypeFiLe);
	public void loadFile(ETypeFiLe eTypeFiLe);

}
