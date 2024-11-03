import { customAxios } from "./helper";

export const sendEmail= async(emailData)=>{
    const result=(await customAxios.post(`/email/send`,emailData)).data;
    return result;
}