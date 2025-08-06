const graficoTemperatura = document.querySelector("#graficoTemperatura");
const graficoUmidade = document.querySelector("#graficoUmidade");
const graficoPressao = document.querySelector("#graficoPressao");
const graficoLuz = document.querySelector("#graficoLuz");
const graficoGas = document.querySelector("#graficoGas");
const graficoAr = document.querySelector("#graficoAr");
const graficoVelocidadeDoVento = document.querySelector("#graficoVelocidadeDoVento");
const graficoVoltagem = document.querySelector("#graficoVoltagem");
const graficoRpm = document.querySelector("#graficoRpm");
const graficoPH = document.querySelector("#graficoPH");
const graficoPluviometro = document.querySelector("#graficoPluviometro");

function colorRangeChange(min, max, value, etapa1, etapa2) {
  const fatorCorrecao = 180 / (max - min);
  const rotation = (value - min) * fatorCorrecao;
  let style;

  if (value < etapa1) {
    style = `--rotation:${rotation}deg; --color:blue; --background:#e9ecef;`;
  } else if (value < etapa2) {
    style = `--rotation:${rotation}deg; --color:yellow; --background:#e9ecef;`;
  } else {
    style = `--rotation:${rotation}deg; --color:red; --background:#e9ecef;`;
  }

  return style;
}

function atualizarGauge(id, valor, min, max, etapa1, etapa2, unidade = "") {
  const elemento = document.querySelector(id);
  if (!elemento || isNaN(valor)) return;
  elemento.style = colorRangeChange(min, max, valor, etapa1, etapa2);
  // Se quiser mostrar o valor com texto, pode adicionar aqui com innerHTML
}

// Função principal para buscar os dados dos endpoints separados
async function preencherGrafico() {
  try {
    // Pega o dado mais recente de cada endpoint
    const [dadosEstacao, sensorPh, pluviometro] = await Promise.all([
      fetch("https://spring-moringa.onrender.com/api/DadosEstacao").then(res => res.json()),
      fetch("https://spring-moringa.onrender.com/api/SensorDePh").then(res => res.json()),
      fetch("https://spring-moringa.onrender.com/api/Pluviometro").then(res => res.json())
    ]);

    const ultimoEstacao = dadosEstacao.at(-1);
    const ultimoPh = sensorPh.at(-1);
    const ultimoPluv = pluviometro.at(-1);

    // Atualiza cada gauge com base nos dados mais recentes
    atualizarGauge("#graficoTemperatura", ultimoEstacao.temperatura, 0, 50, 30, 40, "°C");
    atualizarGauge("#graficoUmidade", ultimoEstacao.umidade, 0, 100, 40, 70, "%");
    atualizarGauge("#graficoPressao", ultimoEstacao.pressao, 900, 1100, 980, 1020, "hPa");
    atualizarGauge("#graficoLuz", ultimoEstacao.luz, 0, 100000, 20000, 50000, "lux");
    atualizarGauge("#graficoGas", ultimoEstacao.gas, 0, 1000, 300, 700, "ppm");
    atualizarGauge("#graficoAr", ultimoEstacao.ar, 0, 500, 100, 200, "AQI");
    atualizarGauge("#graficoVelocidadeDoVento", ultimoEstacao.velocidadeVento, 0, 100, 20, 50, "km/h");
    atualizarGauge("#graficoVoltagem", ultimoEstacao.voltagem, 0, 240, 110, 220, "V");
    atualizarGauge("#graficoRpm", ultimoEstacao.rpm, 0, 10000, 3000, 7000, "rpm");
    atualizarGauge("#graficoPH", ultimoPh.ph, 0, 14, 6, 9);
    atualizarGauge("#graficoPluviometro", ultimoPluv.medidaDeChuvaCalculado, 0, 100, 20, 50, "mm");

  } catch (error) {
    console.error("Erro ao buscar dados dos sensores:", error);
  }
}

// Chamada inicial
preencherGrafico();
