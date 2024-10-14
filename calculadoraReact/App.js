import React, { useState } from 'react';
import { SafeAreaView, StyleSheet, View } from 'react-native';
import { Button, TextInput, Provider as PaperProvider } from 'react-native-paper';

export default function App() {
  const [input, setInput] = useState('');          // Para armazenar o valor digitado
  const [previousValue, setPreviousValue] = useState(null); // Para o valor anterior
  const [operation, setOperation] = useState(null); // Para a operação selecionada

  // Função para adicionar números ao display
  const handlePressNumber = (number) => {
    setInput((prev) => prev + number);
  };

  // Função para limpar o display
  const handleClear = () => {
    setInput('');
    setPreviousValue(null);
    setOperation(null);
  };

  // Função para definir a operação
  const handleOperationPress = (op) => {
    setPreviousValue(input);
    setOperation(op);
    setInput('');
  };

  // Função para calcular o resultado
  const handleEqualPress = () => {
    const current = parseFloat(input);
    const previous = parseFloat(previousValue);

    if (!operation || isNaN(current) || isNaN(previous)) return;

    let result;
    switch (operation) {
      case '+':
        result = previous + current;
        break;
      case '-':
        result = previous - current;
        break;
      case '*':
        result = previous * current;
        break;
      case '/':
        result = previous / current;
        break;
      default:
        return;
    }
    setInput(result.toString());
    setOperation(null);
    setPreviousValue(null);
  };

  return (
    <PaperProvider>
      <SafeAreaView style={styles.container}>
        {/* Display */}
        <TextInput
          mode="outlined"
          style={styles.display}
          value={input}
          editable={false}
        />

        {/* Botões numéricos */}
        <View style={styles.row}>
          {['1', '2', '3'].map((num) => (
            <Button key={num} mode="contained" onPress={() => handlePressNumber(num)} style={styles.button}>
              {num}
            </Button>
          ))}
        </View>
        <View style={styles.row}>
          {['4', '5', '6'].map((num) => (
            <Button key={num} mode="contained" onPress={() => handlePressNumber(num)} style={styles.button}>
              {num}
            </Button>
          ))}
        </View>
        <View style={styles.row}>
          {['7', '8', '9'].map((num) => (
            <Button key={num} mode="contained" onPress={() => handlePressNumber(num)} style={styles.button}>
              {num}
            </Button>
          ))}
        </View>
        <View style={styles.row}>
          <Button mode="contained" onPress={() => handlePressNumber('0')} style={styles.button}>0</Button>
          <Button mode="contained" onPress={handleClear} style={styles.button}>C</Button>
          <Button mode="contained" onPress={handleEqualPress} style={styles.button}>=</Button>
        </View>

        {/* Botões de operação */}
        <View style={styles.row}>
          {['+', '-', '*', '/'].map((op) => (
            <Button key={op} mode="contained" onPress={() => handleOperationPress(op)} style={styles.button}>
              {op}
            </Button>
          ))}
        </View>
      </SafeAreaView>
    </PaperProvider>
  );
}

// Estilos básicos da calculadora
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#f5f5f5',
  },
  display: {
    fontSize: 36,
    marginBottom: 20,
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
  },
  button: {
    width: '22%',
  },
});
