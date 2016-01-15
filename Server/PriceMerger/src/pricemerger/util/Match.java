/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.util;

import pricemerger.data.MasterProductRecord;
import pricemerger.data.MergeProductRecord;

/**
 * Класс, представляющий собой соответствие некоторой степени между записями в основной и входной таблицах
 * @author kiskin
 */
public class Match {
	
	/**
 * Класс, объекты которого отражают степень лексического соответствия между полями товарных позиций во входном и
 * основном файлах. Полнота лексического соответствия зависит от 2 параметров: близости к началу строки и количеству
 * совпавших символов. (предполагается что количество совпавших символов в относительном выражении может не равняться 1)
 *
 * @author kiskin
 */
public static class MatchRate {

	float start; // (0,1]
	float SMC; // Sybmols Matched Count [0, 1]
	
	public MatchRate(float start, float SMC){
		this.start = start;
		this.SMC = SMC;
	}
	
	/**
	 * Сравнивает две величины MatchRate.
	 * @param other MatchRate для сравнения
	 * @return Отрицательное число, если this меньше other. Положительное число, если this больше other. Ноль, если равны.
	 */
	public float compare(MatchRate other){
		//TODO implement
		return 0f;
	}
}
	private final MasterProductRecord masterRecord;
	private final MergeProductRecord mergeRecord;
	
	private final MatchRate matchRate;

	public Match(MasterProductRecord masterRecord, MergeProductRecord mergeRecord, MatchRate matchRate) {
		this.masterRecord = masterRecord;
		this.mergeRecord = mergeRecord;
		this.matchRate = matchRate;
	}
	
	/**
	 * 
	 * @param masterRecord
	 * @param mergeRecord
	 * @param start относительная близость к началу строки 
	 * @param SMC относительное количество совпавших символов
	 */
	public Match(MasterProductRecord masterRecord, MergeProductRecord mergeRecord, float start, float SMC) {
		this.masterRecord = masterRecord;
		this.mergeRecord = mergeRecord;
		this.matchRate = new MatchRate(start, SMC);
	}
	
	
}
