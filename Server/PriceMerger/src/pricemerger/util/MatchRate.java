/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.util;

/**
 * Класс, объекты которого отражают степень лексического соответствия между полями товарных позиций во входном и
 * основном файлах. Полнота лексического соответствия зависит от 2 параметров: близости к началу строки и количеству
 * совпавших символов. (предполагается что количество совпавших символов в относительном выражении может не равняться 1)
 *
 * @author kiskin
 */
public class MatchRate {

	private float start; // (0,1]
	private float SMC; // Sybmols matched count [0, 1]
	
	public MatchRate(float start, float SMC){
		this.start = start;
		this.SMC = SMC;
	}

	/**
	 * @return the start
	 */
	public float getStart() {
		return start;
	}

	/**
	 * @return the SMC
	 */
	public float getSMC() {
		return SMC;
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
