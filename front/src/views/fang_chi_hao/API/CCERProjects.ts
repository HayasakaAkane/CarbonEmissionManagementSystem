import request from "../utils/request";

export  function getProjects(){
    return request({
        method:"GET",
        url:"/ccer/projects"
    })
}

export function getProjectAmounts(){
    return request({
        method: "GET",
        url: "/ccer/projectAmounts",
    })
}

export function getProjectReductionAmounts(){
    return request({
        method: "GET",
        url: "/ccer/projectReductionAmounts",
    })
}

export function putProject(data){
    return request({
        method:"POST",
        url:"/ccer/putProject",
        params:data
    })
}

const CCERProjects = {
    getProjects,
    getProjectAmounts,
    getProjectReductionAmounts,
    putProject
};

export default CCERProjects;