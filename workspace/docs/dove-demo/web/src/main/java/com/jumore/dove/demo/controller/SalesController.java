package com.jumore.dove.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.aop.annotation.PublicMethod;
import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.demo.entity.Sales;
import com.jumore.dove.demo.service.SalesService;
import com.jumore.dove.plugin.Page;
import com.jumore.dove.util.ParamMap;
import com.jumore.dove.web.model.Const;
import com.jumore.dove.web.model.ResponseVo;

@PublicMethod
@Controller
@RequestMapping(value = "/sales")
public class SalesController extends BaseController {

	@Autowired
	private SalesService salesService;

	@RequestMapping(value = "/salesList")
	public ModelAndView SalesList() throws Exception {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "listSalesData")
	public ResponseVo<Page<Sales>> listSalesData(Page<Sales> page) throws Exception {
		ParamMap pm = new ParamMap();
		page = salesService.findPageByParams(Sales.class, page, "Sales.listSales", pm);
		return ResponseVo.<Page<Sales>>BUILDER().setData(page).setCode(Const.BUSINESS_CODE.SUCCESS);
	}

	@RequestMapping(value = "/addSales")
	public ModelAndView addSales() throws Exception {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/doAddSales")
	public ResponseVo<String> doAddSales(Sales entity) throws Exception {
		// TODO validate
		salesService.save(entity);
		return ResponseVo.<String>BUILDER().setData("").setCode(Const.BUSINESS_CODE.SUCCESS);
	}

	@RequestMapping(value = "/editSales")
	public ModelAndView editSales(Integer id) throws Exception {
		ModelAndView mv = new ModelAndView();
		Sales po = salesService.get(Sales.class, id);
		mv.addObject("sales", po);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/doUpdateSales")
	public ResponseVo<String> doUpdateEntity(Sales entity) throws Exception {
		// TODO validate
		Sales po = salesService.get(Sales.class, entity.getId());
		salesService.update(po);
		return ResponseVo.<String>BUILDER().setData("").setCode(Const.BUSINESS_CODE.SUCCESS);
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public ResponseVo<String> delete(Integer id) throws Exception {
		Sales po = salesService.get(Sales.class, id);
		if (po == null) {
			throw new RuntimeException("数据不存在或已删除");
		}
		salesService.delete(po);
		return ResponseVo.<String>BUILDER().setCode(Const.BUSINESS_CODE.SUCCESS);
	}

}
