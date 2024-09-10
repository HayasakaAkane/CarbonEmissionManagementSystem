import request from "../utils/request";

function getProjects(params={}){
    return request({
        method:"GET",
        url:"/ccer/projects",
        params
    })
}