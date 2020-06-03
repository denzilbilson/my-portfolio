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

function showComments(){
    fetch("/data").then(response => response.json()).then((comment) => {
            comment = comment.comments;
            let docObj = document.getElementById("comment-container");
            docObj.innerHTML += "<ul>";
            docObj.innerHTML += "<li><h1>" + comment[0].name + "</h1>" + comment[0].content + "</li>";
            docObj.innerHTML += "<li><h1>" + comment[1].name + "</h1>" + comment[1].content + "</li>";
            docObj.innerHTML += "<li><h1>" + comment[2].name + "</h1>" + comment[2].content + "</li>";
            docObj.innerHTML += "<li><h1>" + comment[3].name + "</h1>" + comment[3].content + "</li>";
            docObj.innerHTML += "</ul>";
            console.log(comment);
            console.log(comment[0].name);

    });
}
