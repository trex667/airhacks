
import BlogService from './BlogService.js';

class BlogApp {

    constructor() {
        this.service = new BlogService();
        this.button = document.querySelector("#blogs");
        this.output = document.querySelector("output");
        this.button.onclick = _ => this.fetchBlogs();
    }

    fetchBlogs() {
        console.log("button clicked");
        this.service.getBlogs().
                then(blogs => this.createList(blogs));

    }

    createList(posts) {
        let ol = document.createElement("ol");
        posts.map(p => this.li(p)).
                forEach(i => ol.appendChild(i));
        this.output.appendChild(ol);

    }

    li(post) {
        let li = document.createElement("li");
        li.innerHTML = `<span>${post.uri}</span> <p>${post.name}</p>`;
        return li;

    }


}

document.app = new BlogApp();