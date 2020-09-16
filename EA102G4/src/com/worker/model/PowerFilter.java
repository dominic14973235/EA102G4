package com.worker.model;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.workpower.model.*;

public class PowerFilter implements Filter {
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.setConfig(config);
	}

	public void destroy() {
		setConfig(null);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		WorkerVO workerVO = (WorkerVO)session.getAttribute("workerVOLogin");

		if (workerVO == null) {
			res.sendRedirect(req.getContextPath() + "/back-end/worker/login.jsp");
			return;
		} else {
			String requrl = req.getRequestURI();
			boolean power00001 = false, power00002 = false, power00003 = false, 
					power00004 = false, power00005 = false, power00006 = false;
			// 01員工 02會員 03課程 04商城05檢舉06食物
			String work_id = workerVO.getWork_id();
			WorkPowerService workpowerSvc = new WorkPowerService();
			List<WorkPowerVO> list = workpowerSvc.getByWorker(work_id);
			for (WorkPowerVO workpowerVO : list) {
				if ("POWER00001".equals(workpowerVO.getPower_id())) {
					power00001 = true;
				}
				if ("POWER00002".equals(workpowerVO.getPower_id())) {
					power00002 = true;
				}
				if ("POWER00003".equals(workpowerVO.getPower_id())) {
					power00003 = true;
				}
				if ("POWER00004".equals(workpowerVO.getPower_id())) {
					power00004 = true;
				}
				if ("POWER00005".equals(workpowerVO.getPower_id())) {
					power00005 = true;
				}
				if ("POWER00006".equals(workpowerVO.getPower_id())) {
					power00006 = true;
				}
			}

			if ((req.getContextPath()+"/back-end/worker/listAllEmp.jsp").equals(requrl)) {
				if (power00001) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			} else if ((req.getContextPath()+"/back-end/worker/addEmp.jsp").equals(requrl)) {
				if (power00001) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			} else if ((req.getContextPath()+"/back-end/mem/listAllMem.jsp").equals(requrl)) {
				if (power00002) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			} else if ((req.getContextPath()+"/back-end/class/listCoachAuth.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/class/listAllClassType.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/class/listCoachClassAuth.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/product/listAllOrder.jsp").equals(requrl)) {
				if (power00004) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/product/listAllProduct.jsp").equals(requrl)) {
				if (power00004) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/report/listAllReport.jsp").equals(requrl)) {
				if (power00005) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/food/listAllFoodBackEnd.jsp").equals(requrl)) {
				if (power00006) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/food/addFood.jsp").equals(requrl)) {
				if (power00006) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else {
				chain.doFilter(request, response);
			}
		}
	}

	public void toIndex(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.sendRedirect(req.getContextPath() + "/back-end/index.jsp");
		return;
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}
}