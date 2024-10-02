import React, { useState } from 'react';
import { StyleSheet, Text, View, TextInput, Button, Alert } from 'react-native';

export default function App() {
  const [inputNumber, setInputNumber] = useState('');

  const handleVerify = () => {
    const randomNumber = Math.floor(Math.random() * 11); // número aleatório entre 0 e 10
    if (parseInt(inputNumber) === randomNumber) {
      Alert.alert("Acertou!");
    } else {
      Alert.alert(`Errou!`);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Digite um valor entre 0 e 10</Text>
      <TextInput
        style={styles.input}
        keyboardType="numeric"
        value={inputNumber}
        onChangeText={text => setInputNumber(text)}
        placeholder="Digite um número"
      />
      <View style={styles.button}>
        <Button title="Verificar" onPress={handleVerify} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 18,
    marginBottom: 20,
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    width: '80%',
    marginBottom: 20,
    paddingHorizontal: 10,
  },
  button: {
    width: '80%',
  },
});
