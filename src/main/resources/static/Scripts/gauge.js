/**
 * 
 * 
 * Este script atualiza e exibe valores em gráficos de medidores (gauge).
 * Ele altera a cor e o ângulo dos medidores com base em valores específicos
 * para temperatura, umidade, pressão, luz, gás, qualidade do ar, velocidade do vento,
 * voltagem e RPM.
 *
 * Funções principais:
 * - colorRangeChange(min, max, value, etapa1, etapa2): Ajusta o estilo do medidor
 *   baseado no valor fornecido, alterando a cor e a rotação.
 * - preencherGrafico(listData): Atualiza os mostradores e os gráficos com os dados
 *   fornecidos.
 *
 * Uso:
 * - Alimente a função `preencherGrafico` com um objeto contendo os dados a serem
 *   exibidos nos gráficos.
 */

const graficoTemperatura = document.querySelector("#graficoTemperatura");
const graficoUmidade = document.querySelector("#graficoUmidade");
const graficoPressao = document.querySelector("#graficoPressao");
const graficoLuz = document.querySelector("#graficoLuz");
const graficoGas = document.querySelector("#graficoGas");
const graficoAr = document.querySelector("#graficoAr");
const graficoVelocidadeDoVento = document.querySelector("#graficoVelocidadeDoVento");
const graficoVoltagem = document.querySelector("#graficoVoltagem");
const graficoRpm = document.querySelector("#graficoRpm");


const mostradorTemperatura = document.querySelector("#mostradorTemperatura");
const mostradorUmidade = document.querySelector("#mostradorUmidade");
const mostradorPressao = document.querySelector("#mostradorPressao");
const mostradorLuz = document.querySelector("#mostradorLuz");
const mostradorGas = document.querySelector("#mostradorGas");
const mostradorAr = document.querySelector("#mostradorAr");
const mostradorVelocidadeDoVento = document.querySelector("#mostradorVelocidadeDoVento");
const mostradorVoltagem = document.querySelector("#mostradorVoltagem");
const mostradorRpm = document.querySelector("#mostradorRpm");

function colorRangeChange(min, max, value, etapa1, etapa2) {
    const fatorCorrecao = 180 / (max - min); // Corrige o fator de rotação
    let rotationStyle;

    if (value < etapa1) {
        rotationStyle = `--rotation:${(value - min) * fatorCorrecao}deg; --color:blue; --background:#e9ecef;`;
    } else if (value < etapa2) {
        rotationStyle = `--rotation:${(value - min) * fatorCorrecao}deg; --color:yellow; --background:#e9ecef;`;
    } else {
        rotationStyle = `--rotation:${(value - min) * fatorCorrecao}deg; --color:red; --background:#e9ecef;`;
    }

    return rotationStyle;
}

 export function preencherGrafico(listData) {
    // Temperatura
    mostradorTemperatura.innerHTML = `${listData.temperatura}°C`;
    graficoTemperatura.style = colorRangeChange(0, 50, listData.temperatura, 30, 40);

    // Umidade
    mostradorUmidade.innerHTML = `${listData.umidade}%`;
    graficoUmidade.style = colorRangeChange(0, 100, listData.umidade, 40, 70);

    // Pressão
    mostradorPressao.innerHTML = `${listData.pressao} hPa`;
    graficoPressao.style = colorRangeChange(900, 1100, listData.pressao, 980, 1020);

    // Luz
    mostradorLuz.innerHTML = `${listData.luz} lux`;
    graficoLuz.style = colorRangeChange(0, 100000, listData.luz, 20000, 50000);

    // Gás
    mostradorGas.innerHTML = `${listData.gas} ppm`;
    graficoGas.style = colorRangeChange(0, 1000, listData.gas, 300, 700);

    // Qualidade do Ar
    mostradorAr.innerHTML = `${listData.ar} AQI`;
    graficoAr.style = colorRangeChange(0, 500, listData.ar, 100, 200);

    // Velocidade do Vento
    mostradorVelocidadeDoVento.innerHTML = `${listData.velocidadeVento} km/h`;
    graficoVelocidadeDoVento.style = colorRangeChange(0, 100, listData.velocidadeVento, 20, 50);

    // Voltagem
    mostradorVoltagem.innerHTML = `${listData.voltagem} V`;
    graficoVoltagem.style = colorRangeChange(0, 240, listData.voltagem, 110, 220);

    // RPM
    mostradorRpm.innerHTML = `${listData.rpm} rpm`;
    graficoRpm.style = colorRangeChange(0, 10000, listData.rpm, 3000, 7000);
}
// Dados de exemplo
const listData = {
    temperatura: 21.24,
    umidade: 51.58,
    pressao: 1021.52,
    luz: 5031,
    gas: 7.3,
    ar: 11.7,
    velocidadeVento: 0,
    voltagem: 0.8719999,
    rpm: 0
};

preencherGrafico(listData);
