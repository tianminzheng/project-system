
package com.tianyalan.common.persistence;

/**
 * 此接口用于要将Mybatis查询出的List结果转化为以map的形式存放的情况.
 * 
 */
public interface MapMapper {

	/**
	 * 产生要放入map中的可以标识这条记录的某个key, <br>
	 * 例如可以以这个对象中的某个属性作为key等.
	 * 
	 * @param object
	 *            list中存放的对象
	 * @param rowNum
	 *            当前的对象号
	 * @return 放入map的关键字
	 */
	public Object mapKey(Object object, int rowNum);

	/**
	 * 产生要放入map中的<code>mapKey()</code> 方法的返回key对应的value, <br>
	 * 例如可以以这个对象中的某个字段的值作为value, 或值对象等
	 * 
	 * @param object
	 *            list中存放的对象
	 * @param rowNum
	 *            当前的对象号
	 * @return 放入map的value
	 */
	public Object mapValue(Object object, int rowNum);

}
