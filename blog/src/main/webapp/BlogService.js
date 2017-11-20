export default class BlogService {

    getBlogs() {
        return fetch("./resources/blogs/").
                then(response => response.json());
    }

}

