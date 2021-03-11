package batch;

public class SendLogMain {
	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("0/5 * * * * ?", SendLogJob.class);	// 1초 마다
		trigger.triggerJob();
	}
}
