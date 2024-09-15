

export  default interface showProjectMessageData {
    projectId: number
    projectName: string;
    projectType: string;
    location: string;
    registrationDate: string | null;
    validUntil: string | null;
    year: number | null;
    reductionAmount: number | null;
    verificationDate: string | null;
    reportDate: string | null;
    reportDetails: string;
    registrationNumber: string;
    issuingAuthority: string;
    issueDate: string | null;
    buyer: string;
    seller: string;
    transactionDate: string | null;
    quantity: number | null;
    pricePerUnit: number | null;
    totalAmount: number | null;
  }