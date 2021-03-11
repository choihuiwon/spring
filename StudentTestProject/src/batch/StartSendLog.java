package batch;

public class StartSendLog {
	public static void main(String[] args) {
		SendLogCronTrigger trigger = new SendLogCronTrigger("0 0 0 1/1 * ? *", SendLogJob.class);	// 매일 자정마다
		trigger.triggerJob();
	}
}
