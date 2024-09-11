import request from "../utils/request";

export  function getProjects(params={}){
    return request({
        method:"GET",
        url:"/ccer/projects",
        params
    })
}