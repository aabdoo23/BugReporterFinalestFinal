import java.time.LocalTime;
public class bug {
    public int ID,type,severity,assignedTo;
    public LocalTime reportTime,fixTime;
    public String description,reportedBy,fixReport;
    bug(){}
    bug(int id,int t,int sev,int assTo,LocalTime repT,LocalTime fixT,String des,String repBy,String fixRpt){
        ID=id;
        type=t;
        severity=sev;
        assignedTo=assTo;
        reportTime=repT;
        fixTime=fixT;
        description=des;
        reportedBy=repBy;
        fixReport=fixRpt;
    }
    public String view(){
        return ("ID: "+ID+"\n Type:"+type+"\n Severity:"+severity+
                "\n Assigned to:"+assignedTo+"\n Report time:"+reportTime +
                "\n Fix time:"+fixTime+"\n Description:"+description+
                "\n Reported by:"+reportedBy+"\n Fix Report:"+fixReport);
    }

}
