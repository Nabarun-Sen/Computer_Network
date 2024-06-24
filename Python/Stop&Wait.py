import time

class Frame:
    def init(self, seq_num, data):
        self.seq_num = seq_num
        self.data = data

def send_frame(frame):
    print(f"Sending frame {frame.seq_num}: {frame.data}")
    time.sleep(1)  # Simulate transmission delay
    return frame

def receive_ack(seq_num):
    ack = input(f"Did you receive ACK for frame {seq_num}? (yes/no): ").lower()
    if ack == 'yes':
        print(f"ACK for frame {seq_num} received.")
        return True
    else:
        print(f"ACK for frame {seq_num} not received.")
        return False

def stop_and_wait(frames):
    n = len(frames)
    seq_num = 0
    while seq_num < n:
        frame = Frame(seq_num, frames[seq_num])
        ack_received = False
        while not ack_received:
            send_frame(frame)
            ack_received = receive_ack(seq_num)
            if not ack_received:
                print(f"Resending frame {seq_num}")
                time.sleep(1)  # Simulate waiting before resending

        seq_num += 1

def main():
    frames = []
    while True:
        frame_data = input("Enter frame data (or 'done' to finish): ")
        if frame_data.lower() == 'done':
            break
        frames.append(frame_data)

    stop_and_wait(frames)

# Directly call the main function
main()