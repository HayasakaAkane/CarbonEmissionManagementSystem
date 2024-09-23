import request from "../utils/request";
import { toRaw } from 'vue';

export function getProjects() {
    return request({
        method: "GET",
        url: "/ccer/projects"
    })
}

export function getProjectAmounts() {
    return request({
        method: "GET",
        url: "/ccer/projectAmounts",
    })
}

export function getProjectReductionAmounts() {
    return request({
        method: "GET",
        url: "/ccer/projectReductionAmounts",
    })
}

export async function putProject(data) {
    console.log(data)

    // return await request.post("/ccer/putProject",JSON.stringify(data))
    return request({
        method: "POST",
        url: "/ccer/putProject",
        // 正确设置请求体
        data: data, // 或者 body: JSON.stringify(rData), 如果使用的是 fetch 或某些其他库
        headers: {
            'Content-Type': 'application/json' // 确保发送的格式是 JSON
        }
    });
}

export function modifyStatus(id:number,s:string){
    return request({
        method:"PUT",
        url: "/ccer/modifyStatus",
        data : {
            projectId: id,
            status: s
        }
    })
}


const CCERProjects = {
    getProjects,
    getProjectAmounts,
    getProjectReductionAmounts,
    putProject,
    modifyStatus
};

export default CCERProjects;