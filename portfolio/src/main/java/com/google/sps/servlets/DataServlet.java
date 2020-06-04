// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class submitComment{
	public String name, content;
	public submitComment(String name, String content){
		this.name = name;
		this.content = content;
	}
}
/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Query query = new Query("Comment").addSort("name", SortDirection.DESCENDING);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery results = datastore.prepare(query);

		List<submitComment> commentList = new ArrayList<>();
		for (Entity entity : results.asIterable()) {
			String userName = (String) entity.getProperty("name");
			String userContent = (String) entity.getProperty("content");
			submitComment user = new submitComment(userName, userContent);
			commentList.add(user);
		}
		Gson gson = new Gson();

		response.setContentType("application/json;");
		response.getWriter().println(gson.toJson(commentList));
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get information from user and store in submitComment object
		// newComment.name = request.getParameter("name");
		// newComment.content = request.getParameter("content");
		submitComment newComment = new submitComment(request.getParameter("name"),request.getParameter("content"));
		Gson gson = new Gson();
		String json = gson.toJson(newComment);

		Entity taskEntity = new Entity("Comment");
		taskEntity.setProperty("name", newComment.name);
		taskEntity.setProperty("content", newComment.content);

		// add information to datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(taskEntity);
		response.sendRedirect("/");
	}
}
