package org.openmrs.module.dicomecg.extension.html;



public class SoAndChen {
	
	private static final int FIRST_ONSET_LENGTH = 400;
	private static final int ONSET_LENGTH = 6;
	private static final int PEAK_DETECT_LENGTH = 100;
	private static final int SAMPLING_RATE = 480;
	private static final int DENOMINATOR = SAMPLING_RATE * 60 * 4;
	
	private int HR_time_count;
	private int MAF_count;
	private int[] MAF_array = new int[5];
	private int HR_MAF_results;
	private int[] HR_array = new int[5];
	private int HR_slope_tmp;
	private int HR_slope;
	
	private boolean setting_onset;
	private boolean detecting_peak;
	
	float maxi;
	float slope_threshold;
	
	private int onset_check;
	private int detect_peak_counter;
	private int HR_onset;
	private int HR_peak_tmp;
	private int HR_peak_addr_tmp;
	private int[] peak_address = new int[5];
	private int peak_address_ptr;
	private float heartrate;
	
	
	public SoAndChen() {
		int i;
		
		HR_time_count = 0;
		MAF_count = 0;
		for (i=0;i<5;i++) {
			MAF_array[i] = 0;
			HR_array[i] = 0;
		}
		HR_MAF_results = 0;
		setting_onset = true;
		detecting_peak = false;
		
		maxi = 0;
		slope_threshold = 0;
		onset_check = 0;
		peak_address_ptr = 0;
		
		heartrate = 0;
	}
	
	public void setData(int data) {
		HR_MAF_process(data + 2048);
		HR_slope_process();
	}
	
	public int getHeartrate() {
		return (int)heartrate;
	}
	
	private void new_Maxi() {
		maxi = ((HR_peak_tmp - HR_onset - maxi) / 2) + maxi; 
	}
	
	private void new_slope() {
		slope_threshold = 0.7f * maxi;
	}
	
	//---moving average
	private void HR_MAF_process(int hr_signal){
		hr_signal = hr_signal >> 3;
		
		MAF_array[0] = MAF_array[1];
		MAF_array[1] = MAF_array[2];
		MAF_array[2] = MAF_array[3];
		MAF_array[3] = MAF_array[4];
		MAF_array[4] = hr_signal;
		
		HR_MAF_results = (MAF_array[0] + MAF_array[1] + MAF_array[2] + MAF_array[3] + MAF_array[4]) / 5;
		
		HR_time_count++;
		HR_slope_tmp = 0;
		HR_slope = 0;
		
		if (HR_time_count > 65535) {
			HR_time_count = 0;
		}
	}
	
	private void HR_slope_process() {
		
		HR_array[0] = HR_array[1];
		HR_array[1] = HR_array[2];
		HR_array[2] = HR_array[3];
		HR_array[3] = HR_array[4];
		HR_array[4] = HR_MAF_results;
		
		//--so and chen process
		HR_slope_tmp = -2 * HR_array[0] - HR_array[1] + HR_array[3] + 2 * HR_array[4];
		
		if(setting_onset){
			if(HR_time_count>5){
				if (HR_slope_tmp > HR_slope) {
					HR_slope = HR_slope_tmp;
				}
				if (HR_time_count == (FIRST_ONSET_LENGTH + 5)) {
					setting_onset = false;
					maxi = HR_slope;
					slope_threshold = maxi * 0.9f;
					onset_check = 0;
				}
			}			
		}
		else{
			if(detecting_peak){
				if (HR_MAF_results > HR_peak_tmp) {
					HR_peak_tmp = HR_MAF_results;
					HR_peak_addr_tmp = HR_time_count;
				}
				detect_peak_counter++;
				if (detect_peak_counter >= PEAK_DETECT_LENGTH) {
					if (peak_address_ptr < 5) {
						peak_address[peak_address_ptr] = HR_peak_addr_tmp;
						peak_address_ptr++;
					} else {
						peak_address[0] = peak_address[1];
						peak_address[1] = peak_address[2];
						peak_address[2] = peak_address[3];
						peak_address[3] = peak_address[4];
						peak_address[4] = HR_peak_addr_tmp;
						
						if (peak_address[4] > peak_address[0]) {
							heartrate = DENOMINATOR / (peak_address[4] - peak_address[0]);
						} else {
							heartrate = DENOMINATOR / (65536 - peak_address[0] + peak_address[4]);
						}
					}
					
					new_Maxi();
					new_slope();
					detecting_peak = false;
				}
			}
			else{
				
				if(HR_slope_tmp > slope_threshold){
					onset_check++;
				}else{
					onset_check = 0;
				}
				
				if(onset_check >= ONSET_LENGTH){
					HR_onset = HR_MAF_results;
					onset_check = 0;
					detecting_peak = true;
					detect_peak_counter = 0;
					
					HR_peak_tmp = HR_onset;
					HR_peak_addr_tmp = HR_time_count;
				}
			}			
		}		
	}
}
