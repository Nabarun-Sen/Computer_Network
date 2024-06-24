def xor(a, b):
    result = []
    for i in range(1, len(b)):
        if a[i] == b[i]:
            result.append('0')
        else:
            result.append('1')
    return ''.join(result)

def mod2div(dividend, divisor):
    pick = len(divisor)
    tmp = dividend[0:pick]

    while pick < len(dividend):
        if tmp[0] == '1':
            tmp = xor(divisor, tmp) + dividend[pick]
        else:
            tmp = xor('0'*pick, tmp) + dividend[pick]
        pick += 1

    if tmp[0] == '1':
        tmp = xor(divisor, tmp)
    else:
        tmp = xor('0'*pick, tmp)

    return tmp

def encode_data(data, key):
    l_key = len(key)
    appended_data = data + '0'*(l_key-1)
    remainder = mod2div(appended_data, key)
    codeword = data + remainder
    return codeword

def decode_data(data, key):
    l_key = len(key)
    remainder = mod2div(data, key)
    return remainder

def main():
    while True:
        print("\n--- CRC Error Detection Protocol ---")
        print("1. Encode Data")
        print("2. Decode Data")
        print("3. Exit")
        choice = input("Enter your choice: ")

        if choice == '1':
            data = input("Enter the data in binary: ")
            key = input("Enter the generator polynomial in binary: ")

            encoded_data = encode_data(data, key)
            print(f"Encoded data with CRC: {encoded_data}")

        elif choice == '2':
            received_data = input("Enter the received data in binary: ")
            key = input("Enter the generator polynomial in binary: ")

            remainder = decode_data(received_data, key)
            print(f"Remainder after division: {remainder}")

            if '1' in remainder:
                print("Error found in received data.")
            else:
                print("No error detected in received data.")

        elif choice == '3':
            print("Exiting program.")
            break

        else:
            print("Invalid choice. Please try again.")

# Call the main function directly
main()
