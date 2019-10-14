package cn.itsource.dao.impl;

import cn.itsource.dao.IProductDao;
import cn.itsource.domain.Product;
import cn.itsource.utils.JPAUtil;

import javax.persistence.*;
import java.util.List;

public class ProductDaoImpl<Product> extends BaseDaoImpl<Product> implements IProductDao<Product>{

}
