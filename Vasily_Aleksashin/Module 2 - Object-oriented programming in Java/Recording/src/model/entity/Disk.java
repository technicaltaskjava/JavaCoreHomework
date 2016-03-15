package model.entity;

import java.util.Arrays;

public class Disk {
	private final long maxDuration;
	private final String label;
	private long currentDuration = 0;
	private Track[] tracks;

	public Disk(final String label, final int maxDiskTimeMinutes) {
		this.maxDuration = maxDiskTimeMinutes * 60 * 1000;
		this.label = (label != null) ? label : "";
	}

	public long getMaxDuration() {
		return maxDuration;
	}

	public String getLabel() {
		return label;
	}

	public Track[] getTracks() {
		return tracks == null ? new Track[0] : tracks;
	}

	public long getCurrentDuration() {
		return currentDuration;
	}

	public boolean addTrack(final Record record) {
		if (tracks == null) {
			tracks = new Track[1];
		}
		if (getAvailableDuration() >= record.getDuration()) {
			while (true) {
				for (int index = 0; index < tracks.length; index++) {
					if (tracks[index] == null) {
						tracks[index] = new Track(record, index + 1);
						currentDuration += tracks[index].getDuration();
						return true;
					}
				}
				tracks = Arrays.copyOf(tracks, tracks.length + 1);
			}
		}
		return false;
	}

	public long getAvailableDuration() {
		return maxDuration - currentDuration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Disk {");
		builder.append("label: ").append(label);
		for (Track track : tracks) {
			if (track != null) {
				builder.append("\n\t").append(track);
			}
		}
		builder.append("}");
		long secs = currentDuration / 1000;
		String formatCurrentDuration = String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60));
		builder.append("\nTotal used duration=").append(formatCurrentDuration);
		return builder.toString();
	}
}
