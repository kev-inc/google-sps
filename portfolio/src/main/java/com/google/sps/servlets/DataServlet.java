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

import java.util.ArrayList; 
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private String aboutme;

  private ArrayList<String> comments;

  @Override
  public void init() {
    aboutme = "Hi! My name is Kevin Chan and I am a 3rd year Computer Science student at the National University of Singapore! " +
        "Specialising in Software Engineering, " + 
        "I enjoy developing mobile applications and websites as each app or website is like a blank canvas for me to exercise my creativity and problem-solving skills when developing them.\n\n" + 
        "During my free time, I enjoy cooking and baking, a skill which I picked up during my exchange programme in London in 2019. " + 
        "Some of my most delicious recipes are brownies, cookies and a bowl of beef gyudon!\n\n" +
        "I also enjoy playing the guitar and the piano, and the songs that I enjoy playing are usually classic pieces " +
        "on the guitar like 'Wonderful Tonight' by Eric Clapton and classical pieces on the piano like the 'Pathetique " +
        "Sonata' by Beethoven.";
    comments = new ArrayList<String>();
    comments.add("Hello world!");
    comments.add("Hello from Singapore!");
    comments.add("Hello from London!");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = toJson(comments);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  private String toJson(ArrayList<String> list) {
      Gson gson = new Gson();
      String json = gson.toJson(list);
      return json;
  }
}
