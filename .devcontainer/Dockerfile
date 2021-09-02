# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.191.1/containers/ubuntu/.devcontainer/base.Dockerfile

# [Choice] Ubuntu version: bionic, focal
ARG VARIANT="focal"
FROM mcr.microsoft.com/vscode/devcontainers/base:0-${VARIANT}

RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add

# [Optional] Uncomment this section to install additional OS packages.
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
        autoconf \
        bison \
        default-jdk \
        g++ \
        git \
        make \
        sbt

# Need to install flex's recommended installs
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
        flex

RUN git clone http://git.veripool.org/git/verilator \
    && cd verilator \
    && git pull	\
    && git checkout v4.016 \
    && autoconf \
    && ./configure \
    && make \
    && make install \
    && cd .. \
    && rm -rf verilator

RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
        gtkwave

# Install SymbiFlow
ENV SYMBIFLOW_INSTALL_DIR=/opt/symbiflow
ENV SYMBIFLOW_FPGA_FAM=xc7
ENV PATH="$SYMBIFLOW_INSTALL_DIR/$SYMBIFLOW_FPGA_FAM/install/bin:$PATH"
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
        git \
        wget \
        xz-utils
RUN git clone https://github.com/SymbiFlow/symbiflow-examples \
    && cd symbiflow-examples \
    && wget https://repo.continuum.io/miniconda/Miniconda3-latest-Linux-x86_64.sh -O conda_installer.sh \
    && bash ./conda_installer.sh -u -b -p $SYMBIFLOW_INSTALL_DIR/$SYMBIFLOW_FPGA_FAM/conda \
    && rm conda_installer.sh \
    && bash -c "source $SYMBIFLOW_INSTALL_DIR/$SYMBIFLOW_FPGA_FAM/conda/etc/profile.d/conda.sh && conda env create -f $SYMBIFLOW_FPGA_FAM/environment.yml" \
    && cd .. \
    && rm -rf symbiflow-examples
RUN mkdir -p $SYMBIFLOW_INSTALL_DIR/xc7/install
RUN wget -qO- https://storage.googleapis.com/symbiflow-arch-defs/artifacts/prod/foss-fpga-tools/symbiflow-arch-defs/continuous/install/361/20210816-133520/symbiflow-arch-defs-install-5bd496ab.tar.xz | tar -xJC $SYMBIFLOW_INSTALL_DIR/xc7/install
RUN wget -qO- https://storage.googleapis.com/symbiflow-arch-defs/artifacts/prod/foss-fpga-tools/symbiflow-arch-defs/continuous/install/361/20210816-133520/symbiflow-arch-defs-xc7a50t_test-5bd496ab.tar.xz | tar -xJC $SYMBIFLOW_INSTALL_DIR/xc7/install
RUN wget -qO- https://storage.googleapis.com/symbiflow-arch-defs/artifacts/prod/foss-fpga-tools/symbiflow-arch-defs/continuous/install/361/20210816-133520/symbiflow-arch-defs-xc7a100t_test-5bd496ab.tar.xz | tar -xJC $SYMBIFLOW_INSTALL_DIR/xc7/install
RUN wget -qO- https://storage.googleapis.com/symbiflow-arch-defs/artifacts/prod/foss-fpga-tools/symbiflow-arch-defs/continuous/install/361/20210816-133520/symbiflow-arch-defs-xc7a200t_test-5bd496ab.tar.xz | tar -xJC $SYMBIFLOW_INSTALL_DIR/xc7/install
RUN wget -qO- https://storage.googleapis.com/symbiflow-arch-defs/artifacts/prod/foss-fpga-tools/symbiflow-arch-defs/continuous/install/361/20210816-133520/symbiflow-arch-defs-xc7z010_test-5bd496ab.tar.xz | tar -xJC $SYMBIFLOW_INSTALL_DIR/xc7/install
