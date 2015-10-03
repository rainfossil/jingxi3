package cn.com.fileload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class LoadFile {

	private HttpServletRequest request=null;
	private int maxSize;
	private String encoding;
	private String serverSavePath;
	DefaultFileRenamePolicy dfrp=new DefaultFileRenamePolicy();
	//构造器初始化上传信息
	public LoadFile(){}
	public LoadFile(HttpServletRequest request,int maxSize,String encoding,String filePath){
		
		this.request=request;
		this.maxSize=maxSize;
		this.encoding=encoding;
		this.serverSavePath=getServerSavePath(filePath);
	}
	//获取服务器上传文件路径
	private String getServerSavePath(String filePath){
		
//		String ssp=request.getRealPath(filePath);
		String ssp=request.getSession().getServletContext().getRealPath("/"+filePath);
		File fi=new File(ssp);
		if(!fi.exists()){
			fi.mkdirs();
		}
		return ssp;
	}
	//获取文件扩展名
	public String getExtName(String fileName){
		
		int start = 0;
		int end = 0;
		if (fileName.indexOf(".") != -1) {
			start = fileName.lastIndexOf(".") + 1;
			end = fileName.length();
		}
		return fileName.substring(start, end);
	}
	//返回可上传文件数组
	public String[] getLoadType(String extNames){
		
		if(extNames==null||extNames==""){
			return null;
		}else{
			return extNames.trim().split(",");
		}
	}
	//无检验上传
	public void simpleLoad() throws IOException{	
	    MultipartRequest mpr=new MultipartRequest(request,serverSavePath,maxSize,encoding,dfrp);
	}

	//可配置上传
	private int i=0;
	public Map<Object,Object> configLoad(String allowLoadType) throws IOException{
		MultipartParser mpp = new MultipartParser(request, maxSize, true,true, encoding);
		Part part=null;
		Map<Object,Object> map=new HashMap<Object,Object>();
		while((part = mpp.readNextPart())!= null) {
			if(part.isFile()){
				FilePart file = (FilePart) part;
				if(file.getFileName()!=null){
					String extName = getExtName(file.getFileName());
					String[] extNames =getLoadType(allowLoadType);					
					if(extNames!=null){
						boolean flag=false;
						for(String ext:extNames){
							if(extName.equals(ext)){
								flag=true;
								break;
							}
						}
							if(flag){
								file.setRenamePolicy(dfrp);
								file.writeTo(new File(serverSavePath));
								//String newFileName=dfrp.rename(new File(file.getFileName())).getName();
								map.put(file.getFileName(), String.valueOf(i));
								i++;
							}else{
								System.out.println("未上传.........");
							}
					}else{
						file.setRenamePolicy(dfrp);
						file.writeTo(new File(serverSavePath));
						//String newFileName=dfrp.rename(new File(file.getFileName())).getName();
						//System.out.println(newFileName);
						//System.out.println(file.getFileName()+"#####################");
						map.put(file.getFileName(), String.valueOf(i));
						i++;
					}
				}
			}else{
				ParamPart paramPart = (ParamPart) part;
				String paramName = paramPart.getName();
				String paramValue = paramPart.getStringValue();
				map.put(paramName, paramValue);
			}
		}
		return map;
	}
	public static void main(String[]args){
//		HttpServletRequest request=null;
		 LoadFile lf=new LoadFile();
		 String[]sss=lf.getLoadType("api,txt,jpg");
		 if(sss==null){
			 System.out.println("#################");
		 }else{
			 System.out.println("&&&&&&&&&&&&&&&&");
		 }
		 for(String str:sss){
			 System.out.println(str);
		 }
		 System.out.println(lf.getExtName("da.ta.jpg"));
	}
}

