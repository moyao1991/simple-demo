/**
 *  存储数据库表对应模型,与demo-application，采用copy-on-write 机制
 *
 *  1. 如果数据库模板和demo-application所需模型无差异，直接使用demo-application
 *  2. 如果存在差异，则新建实体类，变使用converter进行转换
 */
package com.moyao.demo.infra.db.model;