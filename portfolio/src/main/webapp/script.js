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


fetch('/data').then(response => response.json()).then((comments) => {
	let commentSection = document.getElementById('comments-container');
    
	for(x in comments){

        commentSection.innerHTML += addComment(comments[x]);
	}
});

function addComment(comment){
    let string = `<li><h3> ${comment.name} </h3>`;
    string += `<p> ${comment.content} </p></li>`;
    return string;
}