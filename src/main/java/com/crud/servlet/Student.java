package com.crud.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.classfmt.NonNullDefaultAwareTypeAnnotationWalker;

import com.crud.been.StudentBeen;
import com.crud.dao.StduentDao;
import com.google.gson.Gson;

@MultipartConfig
@WebServlet("/student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Student() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		com.crud.dao.StduentDao dao = new StduentDao();
		if(request.getParameter("id")==null) {
			List<StudentBeen> students = dao.getData();
			
			if(students.size()==0) {
				response.setContentType("application/json");
				response.setStatus(404);
				out.println("Record Not Found");
			}else {
				response.setContentType("application/json");
				response.setStatus(200);
				out.println(new Gson().toJson(students));
			}
		}else {
			int std_id = Integer.parseInt(request.getParameter("id"));
			StudentBeen student = dao.getStudent(std_id);
			if(student!=null)
			{
				response.setStatus(200);
				out.println(new Gson().toJson(student));	
			}else {
				response.setStatus(404);
				out.println("Student Not Found");
			}
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out =  response.getWriter();
		String id = request.getParameter("id");
		int update_id = Integer.parseInt(id);
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		if(update_id ==0) {
			StudentBeen been = new StudentBeen(0, name, mobile, email);
			com.crud.dao.StduentDao dao = new com.crud.dao.StduentDao();
			boolean isSaved = dao .save(been);			
			if(isSaved) {
				response.setStatus(200);
				out.print("Done");
			}
			else {
				response.setStatus(500);
				out.print("faild");	}
		}else {
			StudentBeen been = new StudentBeen(update_id, name, mobile, email);
			com.crud.dao.StduentDao dao = new com.crud.dao.StduentDao();
			boolean isUpdate = dao .update(been);			
			if(isUpdate) {
				response.setStatus(200);
				out.print("update");
				} 
			else {
				response.setStatus(500);
				out.print("update faild");
			}
			
		}
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String user_id =  request.getParameter("id");
		int id = Integer.parseInt(user_id);
		com.crud.dao.StduentDao dao = new com.crud.dao.StduentDao();
		
		boolean isDelete =  dao.delete(id);
		
		if(isDelete) {
			response.setStatus(200);
			out.println("done");
		}else {
			response.setStatus(500);
			out.println("error");
		}

	}

}
